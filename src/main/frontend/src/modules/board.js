import axios from "axios";

const BOARD_URL = 'http://localhost:8080/board/';

// Inital State
const initalState = {
    board: null,
};

// Action
const BOARD_LIST = 'BOARD_LIST';

export const createBoard = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.get(BOARD_URL, dataToSubmit);

        dispatch ({
            type: BOARD_LIST,
            payload: response,
        });
    } catch (error) {
        console.error(error);
    };
};

// Reducer
export default function reducer(state = initalState, action) {
    switch (action.type) {
        case BOARD_LIST:
            return {
                ...state,
                board: action.payload,
            };
        default:
            return state;
    };
};