<template>
  <div class="container">
    <div class="box">
      <h1>world of zuul</h1>
      <h3>祖尔的世界</h3>
      <button @click="$router.push({ path: '/game' })">开始游戏</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
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
