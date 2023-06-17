<template>
  <div class="container">
    <!-- 地图区 -->
    <div class="map">
      <div class="wall-1"></div>
      <div class="wall-2"></div>
      <div class="wall-3"></div>
      <div class="wall-4"></div>
      <div class="player" @click="showPlayerInfo">
        <img src="@/assets/player.png" />
      </div>

      <p class="room-name">{{ room.name }}</p>
      <img
        v-if="room.exitRoomIdMap['north'] != null"
        class="arrow up"
        src="@/assets/up.png"
        @click="move('north')"
      />
      <img
        v-if="room.exitRoomIdMap['east'] != null"
        class="arrow right"
        src="@/assets/right.png"
        @click="move('east')"
      />
      <img
        v-if="room.exitRoomIdMap['south'] != null"
        class="arrow down"
        src="@/assets/down.png"
        @click="move('south')"
      />
      <img
        v-if="room.exitRoomIdMap['west'] != null"
        class="arrow left"
        src="@/assets/left.png"
        @click="move('west')"
      />
      <div class="items-box">
        <div class="item" v-for="obj in itemsInRoom" :key="obj.id">
          <img @click="take(obj.id)" src="@/assets/item.png" />
          <p>{{ obj.description }}</p>
        </div>
      </div>
    </div>

    <!-- 底部操作区 -->
    <div class="control">
      <button @click="back">Back</button>
      <button @click="Look">Look</button>
      <button @click.stop="openDrop">drop</button>
      <button @click="Help">Help</button>
      <button @click="$router.push({ path: '/home' })">退出游戏</button>
    </div>
    <!-- 房间内容 -->
    <div class="info-box">
      <div class="head">
        <p @click="Close">x</p>
      </div>
      <div class="content">
        <p>房间描述：{{ room.description }}</p>
        <p>房间物品</p>
        <p v-for="obj in itemsInRoom" :key="obj.id">
          物品名：{{ obj.description }}，重量：{{ obj.weight }}
        </p>
      </div>
    </div>
    <!-- 帮助内容 -->
    <div class="help-box">
      <div class="head">
        <p @click="Close">x</p>
      </div>
      <div class="content">
        <p>You are lost. You are alone.</p>
        <p>You wander around at the university.</p>
        <p>You need to help zuul to explore the world.</p>
      </div>
    </div>
    <div class="player-box">
      <div class="head">
        <p @click="Close">x</p>
      </div>
      <div class="content">
        <p>用户名：{{ player.name }}</p>
        <p>背包上限：{{ player.maxCarryWeight }}</p>
        <p>当前房间id：{{ player.currentRoomId }}</p>
      </div>
    </div>
    <div class="drop-box">
      <div class="head">
        <p @click="Close">x</p>
      </div>
      <div class="content" v-for="obj in itemsOfPlayer" :key="obj.id">
        <div class="item-list">
          <p>物品id：{{ obj }}</p>
          <button @click="drop(obj)">drop</button>
        </div>
      </div>
    </div>
    <div v-if="msg != ''" class="prompt-box">
      <p>{{ msg }}</p>
    </div>
  </div>
</template>

<script>
import {
  getPlayerInfo,
  getRoomInfo,
  getItemsInRoom,
  moveTowardsDirection,
  BackLastRoom,
  TakeItems,
  DropItems,
} from "@/api";

export default {
  data() {
    return {
      player: {},
      msg: "",
      room: {
        name: "房间加载中",
        exitRoomIdMap: {},
      },
      itemsInRoom: [],
      itemsOfPlayer: [],
    };
  },
  async mounted() {
    await this.syncGameStatus();
  },
  methods: {
    async syncGameStatus() {
      this.player = await getPlayerInfo();
      this.room = await getRoomInfo(this.player.currentRoomId);
      this.itemsInRoom = await getItemsInRoom(this.player.currentRoomId);
    },
    async move(direction) {
      const res = await moveTowardsDirection(direction);
      if(res.status == 'TransferSuccess'){
      this.msg='即将传送...';
      setTimeout(()=>{this.msg="";},1000)
      }

      const playerDocument = document.querySelector(".player");
      //根据方向改变人物移动方向及归位
      if (direction == "north") {
        playerDocument.style.top = "0%";
        playerDocument.style.transition = "top 1s linear";
        setTimeout(async () => {
          await this.syncGameStatus();
          playerDocument.style.transition = "none";
          playerDocument.style.top = "40%";
        }, 1000);
      }
      if (direction == "east") {
        playerDocument.style.left = "90%";
        playerDocument.style.transition = "left 1s linear";
        setTimeout(async () => {
          await this.syncGameStatus();
          playerDocument.style.transition = "none";
          playerDocument.style.left = "46%";
        }, 1000);
      }
      if (direction == "south") {
        playerDocument.style.top = "80%";
        playerDocument.style.transition = "top 1s linear";
        setTimeout(async () => {
          await this.syncGameStatus();
          playerDocument.style.transition = "none";
          playerDocument.style.top = "40%";
        }, 1000);
      }
      if (direction == "west") {
        playerDocument.style.left = "0%";
        playerDocument.style.transition = "left 1s linear";
        setTimeout(async () => {
          await this.syncGameStatus();
          playerDocument.style.transition = "none";
          playerDocument.style.left = "46%";
        }, 1000);
      }

      setTimeout(()=>{
      if(this.player.currentRoomId == 0){
      alert('transfer');
      }},1000
      )
    },
    async back() {
      await BackLastRoom();
      await this.syncGameStatus();
    },
    //显示look内容
    Look() {
      const info_box = document.querySelector(".info-box");
      info_box.style.opacity = "1";
      info_box.style.zIndex = "2";
    },
    //显示help内容
    Help() {
      const help_box = document.querySelector(".help-box");
      help_box.style.opacity = "1";
      help_box.style.zIndex = "2";
    },
    //关闭上面两个功能
    Close() {
      const info_box = document.querySelector(".info-box");
      info_box.style.opacity = "0";
      info_box.style.zIndex = "0";
      const help_box = document.querySelector(".help-box");
      help_box.style.opacity = "0";
      help_box.style.zIndex = "0";
      const player_box = document.querySelector(".player-box");
      player_box.style.opacity = "0";
      player_box.style.zIndex = "0";
      const drop_box = document.querySelector(".drop-box");
      drop_box.style.opacity = "0";
      drop_box.style.zIndex = "0";
    },
    showPlayerInfo() {
      const player_box = document.querySelector(".player-box");
      player_box.style.opacity = "1";
      player_box.style.zIndex = "2";
    },
    async take(item_id) {
      const res = await TakeItems(item_id);
      if (res.status == "NotInCurrentRoom") {
        this.msg = "该物品已被拾起或不再该房间！";
        setTimeout(() => {
          this.msg = "";
        }, 1000);
      } else if (res.status == "ExceedMaxCarryWeight") {
        this.msg = "已达背包上限，请丢弃一些物品！";
        setTimeout(() => {
          this.msg = "";
        }, 1000);
      } else {
        this.itemsOfPlayer.push(item_id);
        await this.syncGameStatus();
        this.msg = "拾起成功！";
        setTimeout(() => {
          this.msg = "";
        }, 1000);
      }
    },
    openDrop() {
      const drop_box = document.querySelector(".drop-box");
      drop_box.style.opacity = "1";
      drop_box.style.zIndex = "2";
    },
    async drop(item_id) {
      const res = await DropItems(item_id);
      if (res.status == "NotCarry") {
        this.msg = "无法丢弃！";
        setTimeout(() => {
          this.msg = "";
        }, 1000);
      } else {
        this.msg = "丢弃成功！";
        setTimeout(() => {
          this.msg = "";
        }, 1000);
        for (var i = 0; i < this.itemsOfPlayer.length; i++) {
          if (this.itemsOfPlayer[i] == item_id) {
            this.itemsOfPlayer.splice(i, 1);
            await this.syncGameStatus();
          }
        }
      }
    },
  },
};
</script>

<style lang="less" scoped>
.container {
  height: 100vh;
  width: 100%;
  background: linear-gradient(45deg, rgb(65, 65, 65), black);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: SThupo;

  .map {
    height: 85%;
    width: 100%;
    background: transparent;

    .wall-1 {
      height: 30%;
      width: 30%;
      background: transparent;
      border-right: 5px solid white;
      border-bottom: 5px solid white;
      position: fixed;
      top: 0%;
      left: 0%;
    }

    .wall-2 {
      height: 30%;
      width: 30%;
      background: transparent;
      border-left: 5px solid white;
      border-bottom: 5px solid white;
      position: fixed;
      top: 0%;
      left: 70%;
    }

    .wall-3 {
      height: 30%;
      width: 30%;
      background: transparent;
      border-right: 5px solid white;
      border-top: 5px solid white;
      position: fixed;
      top: 54%;
      left: 0%;
    }

    .wall-4 {
      height: 30%;
      width: 30%;
      background: transparent;
      border-left: 5px solid white;
      border-top: 5px solid white;
      position: fixed;
      top: 54%;
      left: 70%;
    }

    .player {
      height: 120px;
      width: 120px;
      position: relative;
      top: 40%;
      left: 45%;
      cursor: pointer;
      z-index: 1;
      img {
        height: 100%;
        width: 100%;
      }
      .drop{
      height:50px;
      width:100px;
      border-radius:20px;
      color: white;
      font-size:20px;
      background: rgb(56,56,56);
      border: none;
      cursor:pointer;
      }
    }

    .arrow {
      height: 80px;
      width: 80px;
      cursor: pointer;
      animation: jump 0.5s infinite alternate;
    }

    @keyframes jump {
      0% {
        transform: scale(1);
      }
      100% {
        transform: scale(1.5);
      }
    }

    .arrow.up {
      position: fixed;
      top: 0%;
      left: 46%;
    }

    .arrow.down {
      position: fixed;
      top: 75%;
      left: 46%;
    }

    .arrow.left {
      position: fixed;
      top: 38%;
      left: 0%;
    }

    .arrow.right {
      position: fixed;
      top: 38%;
      right: 0%;
    }

    .room-name {
      font-size: 30px;
      color: white;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }

    .items-box {
      width: 100%;
      display: flex;
      justify-content: space-around;
      align-items: center;

      .item {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        cursor: pointer;
        z-index: 1;
        img {
          height: 70px;
          width: 70px;
        }

        p {
          color: #fff;
          font-size:20px;
        }
      }
    }
  }

  .control {
    height: 15%;
    width: 100%;
    background: transparent;
    display: flex;
    justify-content: space-around;
    align-items: center;

    button {
      height: 80px;
      width: 15%;
      border-radius: 50px;
      font-size: 40px;
      cursor: pointer;
      border: none;
      font-family: SThupo;
      background: rgb(88,88,88);
      color: white;
    }
  }

  .info-box,
  .help-box,
  .player-box,
  .drop-box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 350px;
    width: 500px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    opacity: 0;
    transition: opacity 0.6s linear;
    border-radius: 20px;

    .head {
      display: flex;
      align-items: center;
      background: linear-gradient(45deg, rgb(65, 65, 65), rgb(30, 30, 30));
      height: 20%;
      width: 100%;

      p {
        margin: 93%;
        color: white;
        font-size: 30px;
        cursor: pointer;
      }
    }

    .content {
      background: #fff;
      height: 80%;
      width: 100%;
      overflow-x: hidden;

      p {
        padding: 0 10px 0 10px;
      }
    }
  }

  .drop-box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .content {
      .item-list {
        display: flex;
        justify-content: space-between;
        align-items: center;
        button {
          cursor: pointer;
          height: 50px;
          width: 120px;
          background: rgb(56, 56, 56);
          border-radius: 30px;
          margin: 10px;
          font-size: 20px;
          color: white;
          border: none;
        }
      }
    }
  }

  .prompt-box {
    height: 300px;
    width: 300px;
    border-radius: 20px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    z-index: 2;
  }
}
</style>
