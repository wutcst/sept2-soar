import request from "@/util/request.js";

export const getNewGame = ({ }) => request({
    url: '/new_game'
})

export const getContinueGame = ({ }) => request({
    url: '/continue_game'
})

export const getNextRoom = ({ direction }) => request({
    //url: `/go/${direction}`
    url: '/go'
})

export const BackLastRoom = ({ }) => request({
    url: '/back'
})

export const TransferRoom = ({ }) => request({
    url: '/transfer'
})