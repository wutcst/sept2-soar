import request from "@/util/request.js";

export const getPlayerInfo = () => request({
    url: '/player'
})

// export const getContinueGame = ({ }) => request({ //获取上一次游戏
//     url: '/continueGame'
// })

export const getRoomInfo = (id) => request({
  url: `/room/${id}`
})

export const getItemsInRoom = (id) => request({
  url: `/room/${id}/items`
})

export const moveTowardsDirection = (direction) => request({
  url: `/move/${direction}`
})

export const getNextRoom = ({ direction }) => request({ //go请求
    url: `/go?direction=${direction}`
})

export const BackLastRoom = () => request({ //back请求
    url: '/back'
})

export const TransferRoom = ({ }) => request({ //传送请求
    url: '/transfer'
})
