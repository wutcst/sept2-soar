<template>
  <div class="container">
    <!-- 地图区 -->
    <div class="map">
      <div class="wall-1"></div>
      <div class="wall-2"></div>
      <div class="wall-3"></div>
      <div class="wall-4"></div>
      <img class="player" src="@/assets/player.png" />
      <p class="room-name">{{ game.name }}</p>
      <img
        v-if="game.exits[0] == true"
        class="arrow up"
        src="@/assets/up.png"
        @click="Go('north')"
      />
      <img
        v-if="game.exits[1] == true"
        class="arrow right"
        src="@/assets/right.png"
        @click="Go('east')"
      />
      <img
        v-if="game.exits[2] == true"
        class="arrow down"
        src="@/assets/down.png"
        @click="Go('south')"
      />
      <img
        v-if="game.exits[3] == true"
        class="arrow left"
        src="@/assets/left.png"
        @click="Go('west')"
      />
      <div class="items-box">
        <div class="item" v-for="obj in game.items" :key="obj.id">
          <img src="@/assets/item.png" />
          <p>{{ obj.description }}</p>
        </div>
      </div>
    </div>
    <!-- 底部操作区 -->
    <div class="control">
      <button @click="Back()">Back</button>
      <button @click="Look">Look</button>
      <button @click="Help">Help</button>
      <button @click="$router.push({ path: '/home' })">退出游戏</button>
    </div>
    <!-- 房间内容 -->
    <div class="info-box">
      <div class="head">
        <p @click="Close">x</p>
      </div>
      <div class="content">
        <p>房间描述：{{ game.description }}</p>
        <p>房间物品</p>
        <p v-for="obj in game.items" :key="obj.id">
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
    <!-- 提示内容 -->
    <div v-if="isTransfer" class="prompt-box">这是传送房间，即将传送...</div>
    <div v-if="!isBack" class="prompt-box">回到起点或被传送，不可返回！</div>
  </div>
</template>

<script>
import {
  getContinueGame,
  getNewGame,
  getNextRoom,
  BackLastRoom,
  TransferRoom,
} from "@/api";
export default {
  data() {
    return {
      game: {}, //当前游戏对象，保存当前房间信息
      isBack: true, //判断是否显示不可返回提示
      isTransfer: false, //判断是否显示传送提示
    };
  },
  async mounted() {
    if (this.$route.query.isNew == 1) {
      //当前路由携带参数为1则表示新游戏
      const res1 = await getNewGame({});
      console.log(res1);
      this.game = res1.data;
    } 
    // else {
    //   //当前路由携带参数为0表示继续游戏
    //   const res2 = await getContinueGame({});
    //   console.log(res2);
    //   this.game = res2.data;
    // }
  },
  methods: {
    //向后端请求go后的数据
    async Go(direction) {
      const res = await getNextRoom({
        direction: direction,
      });
      console.log(res);
      const player = document.querySelector(".player");
      //根据方向改变人物移动方向及归位
      if (direction == "north") {
        player.style.top = "0%";
        player.style.transition = "top 1s linear";
        setTimeout(() => {
          this.game = res.data;
          player.style.transition = "none";
          player.style.top = "40%";
        }, 1000);
      }
      if (direction == "east") {
        player.style.left = "90%";
        player.style.transition = "left 1s linear";
        setTimeout(() => {
          this.game = res.data;
          player.style.transition = "none";
          player.style.left = "46%";
        }, 1000);
      }
      if (direction == "south") {
        player.style.top = "80%";
        player.style.transition = "top 1s linear";
        setTimeout(() => {
          this.game = res.data;
          player.style.transition = "none";
          player.style.top = "40%";
        }, 1000);
      }
      if (direction == "west") {
        player.style.left = "0%";
        player.style.transition = "left 1s linear";
        setTimeout(() => {
          this.game = res.data;
          player.style.transition = "none";
          player.style.left = "46%";
        }, 1000);
      }
      //根据房间名判断是否为传送房间，是则请求传送后的数据
      setTimeout(async () => {
        if (this.game.name == "tpRoom") {
          this.isTransfer = true;
          const res = await TransferRoom({});
          console.log(res);
          setTimeout(() => {
            this.game = res.data;
            this.isTransfer = false;
          }, 1000);
        }
      }, 1200);
    },
    //向后端请求back后的数据
    async Back() {
      const res = await BackLastRoom({});
      console.log(res);
      //如果当前房间名和请求到的数据一致，表示不可返回
      if (res.data.name == this.game.name) {
        this.isBack = false;
        setTimeout(() => {
          this.isBack = true;
        }, 1000);
      } else {
        this.game = res.data;
      }
    },
    //显示look内容
    Look() {
      const info_box = document.querySelector(".info-box");
      info_box.style.opacity = "1";
    },
    //显示help内容
    Help() {
      const help_box = document.querySelector(".help-box");
      help_box.style.opacity = "1";
    },
    //关闭上面两个功能
    Close() {
      const info_box = document.querySelector(".info-box");
      info_box.style.opacity = "0";
      const help_box = document.querySelector(".help-box");
      help_box.style.opacity = "0";
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
        img {
          height: 70px;
          width: 70px;
        }
        p {
          color: #fff;
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
      width: 20%;
      border-radius: 50px;
      font-size: 40px;
      cursor: pointer;
      border: none;
    }
  }
  .info-box,
  .help-box {
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
  }
}
</style>
