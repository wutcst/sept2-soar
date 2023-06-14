import Mock from "mockjs"

import newgame from "./newgame.json";
import go from "./go.json";
import back from "./back.json";
import continuegame from "./continuegame.json";
import transfer from "./transfer.json";

Mock.mock('/mock/new_game', { code: 200, data: newgame });
Mock.mock('/mock/go', { code: 200, data: go });
Mock.mock('/mock/back', { code: 200, data: back });
Mock.mock('/mock/continue_game', { code: 200, data: continuegame });
Mock.mock('/mock/transfer', { code: 200, data: transfer });
