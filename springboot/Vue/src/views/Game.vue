<template>
  <div class="container">
    <div class="map">
      <div class="wall-1"></div>
      <div class="wall-2"></div>
      <div class="wall-3"></div>
      <div class="wall-4"></div>
      <img class="player" src="@/assets/player.png" />
      <img class="arrow up" src="@/assets/up.png" />
      <img class="arrow down" src="@/assets/down.png" />
      <img class="arrow left" src="@/assets/left.png" />
      <img class="arrow right" src="@/assets/right.png" />
    </div>
    <div class="control">
      <button>Back</button>
      <button>Look</button>
      <button>退出游戏</button>
    </div>
  </div>
</template>

<script>
export default {};
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
      height: 100px;
      width: 100px;
      position: relative;
      top: 40%;
      left: 46%;
    }
    .arrow {
      height: 80px;
      width: 80px;
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
      width: 30%;
      border-radius: 50px;
      font-size: 40px;
      cursor: pointer;
      border: none;
    }
  }
}
</style>
