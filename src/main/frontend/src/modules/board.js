import axios from "axios";

const BOARD_URL = 'http://localhost:8080/board/';

// Inital State
const initalState = {
    board: null,
    boardList: [],
};

// Action
const CREATE_BOARD = 'CREATE_BOARD';
const BOARD_LIST = 'BOARD_LIST';

export const createBoard = (dataToSubmit) => async dispatch => {
    try {
        const accessToken = localStorage.getItem('accessToken');

        const headers = {
            Authorization: `Bearer ${accessToken}`
        };

        const response = await axios.post(BOARD_URL + 'save', dataToSubmit, { headers });

        dispatch ({
            type: CREATE_BOARD,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const boardList = () => async dispatch => {
    try {
        const accessToken = localStorage.getItem('accessToken');

        const headers = {
            Authorization: `Bearer ${accessToken}`
        };

        const response = await axios.get(BOARD_URL, { headers });

        console.log(response);

        dispatch ({
            type: BOARD_LIST,
            payload: response.data,
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
                boardList: action.payload,
            };
        default:
            return state;
    };
};