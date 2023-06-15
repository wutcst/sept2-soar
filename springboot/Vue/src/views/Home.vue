<template>
  <div class="container">
    <div class="box">
      <h1>world of zuul</h1>
      <h3>祖尔的世界</h3>
      <button @click="createGame">开始游戏</button>
      <button v-if="last_game.name != null" @click="continueGame">
        继续游戏
      </button>
    </div>
  </div>
</template>

<script>
// import { getContinueGame } from "@/api";
export default {
  data() {
    return {
      last_game: {}, //保存上一次游戏的内容
    };
  },
  //获取上一次游戏的数据，依此判断是否显示继续游戏按钮
  // async mounted() {
  //   const res = await getContinueGame({});
  //   this.last_game = res.data;
  // },
  methods: {
    //携带参数1跳转到游戏界面
    createGame() {
      this.$router.push({ path: "/game", query: { isNew: 1 } });
    },
    //携带参数0跳转到游戏界面
    continueGame() {
      this.$router.push({ path: "/game", query: { isNew: 0 } });
    },
  },
};
</script>

<style lang="less" scoped>
.box {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100vh;
  width: 100%;
  background: linear-gradient(45deg, rgb(56, 56, 56), black);
  h1 {
    font-size: 100px;
    font-family: SThupo;
    color: white;
    animation: light 1.5s infinite alternate;
  }

  h3 {
    margin-top: -50px;
    font-size: 50px;
    font-family: SThupo;
    color: white;
    animation: light 1.5s infinite alternate;
  }
  @keyframes light {
    0% {
      color: rgb(56, 56, 56);
    }
    100% {
      color: white;
      text-shadow: 0 0 10px #03e9f4;
    }
  }
  button {
    height: 100px;
    width: 350px;
    border-radius: 50px;
    font-size: 50px;
    font-family: SThupo;
    cursor: pointer;
    background: rgb(65, 65, 65);
    border: none;
    color: #625f5f;
    margin-bottom: 20px;
    &:hover {
      color: white;
      text-shadow: 0 0 10px #03e9f4;
      border: 5px solid white;
      box-shadow: 0 0 10px #03e9f4;
    }
  }
}
</style>