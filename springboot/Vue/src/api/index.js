import request from "@/util/request.js";

export const getNewGame = ({ }) => request({ //创建游戏，获取初始房间
    url: '/createGame'
})

// export const getContinueGame = ({ }) => request({ //获取上一次游戏
//     url: '/continueGame'
// })

export const getNextRoom = ({ direction }) => request({ //go请求
    url: `/go?direction=${direction}`
})

export const BackLastRoom = ({ }) => request({ //back请求
    url: '/back'
})

export const TransferRoom = ({ }) => request({ //传送请求
    url: '/transfer'
})
