import request from "@/util/request.js";

export const getPlayerInfo = () => request({ //获取玩家信息
  url: '/player'
})

export const getPlayerItems = () => request({ //获取玩家背包
  url: 'player/items'
})

export const getRoomInfo = (id) => request({ //获取房间信息
  url: `/room/${id}`
})

export const getItemsInRoom = (id) => request({ //获取房间内的物品
  url: `/room/${id}/items`
})

export const moveTowardsDirection = (direction) => request({ //前往下一个房间
  url: `/move/${direction}`
})


export const BackLastRoom = () => request({ //返回上一个房间
  url: '/back'
})

export const TransferRoom = ({ }) => request({ //传送请求
  url: '/transfer'
})

export const TakeItems = (id) => request({ //拾起物品
  url: `/take/${id}`
})

export const DropItems = (id) => request({ //丢弃物品
  url: `/drop/${id}`
})
