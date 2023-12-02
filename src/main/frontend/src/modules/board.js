import axios from "axios";

const BOARD_URL = 'http://localhost:8080/board/';

// Inital State
const initalState = {
    board: null,
};

// Action
const CREATE_BOARD = 'CREATE_BOARD';
const BOARD_LIST = 'BOARD_LIST';

export const createBoard = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(BOARD_URL + 'save', dataToSubmit);

        dispatch ({
            type: CREATE_BOARD,
            payload: response,
        });
    } catch (error) {
        console.error(error);
    };
};

export const boardList = () => async dispatch => {
    try {
        const response = await axios.get(BOARD_URL);

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
        case CREATE_BOARD:
            return {
                ...state,
                board: action.payload,
            };
        case BOARD_LIST:
            return {
                ...state,
                board: action.payload,
            };
        default:
            return state;
    };
};