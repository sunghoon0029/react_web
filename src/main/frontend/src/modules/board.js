import axios from "axios";

const BOARD_URL = 'http://localhost:8080/board/';

// Inital State
const initalState = {
    board: null,
    boardList: [],
    boardPage: null,
};

// Action
const CREATE_BOARD = 'CREATE_BOARD';
const BOARD_LIST = 'BOARD_LIST';
const BOARD_PAGE = 'BOARD_PAGE';
const GET_BOARD = 'GET_BOARD';
const UPDATE_BOARD = 'UPDATE_BOARD';
const DELETE_BOARD = 'DELETE_BOARD';

const accessToken = localStorage.getItem('accessToken');

const headers = {
    Authorization: `Bearer ${accessToken}`
};

export const createBoard = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(BOARD_URL + 'save', dataToSubmit, {headers});

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
        const response = await axios.get(BOARD_URL, {headers});

        console.log(response);

        dispatch ({
            type: BOARD_LIST,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const boardPage = () => async dispatch => {
    try {
        const response = await axios.get(BOARD_URL + 'page', {headers});

        console.log(response);

        dispatch ({
            type: BOARD_PAGE,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const getBoard = (id) => async dispatch => {
    try {
        const response = await axios.get(BOARD_URL + `${id}`, {headers});

        console.log(response);

        dispatch ({
            type: GET_BOARD,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const updateBoard = (id, dataToSubmit) => async dispatch => {
    try {
        const response = await axios.put(BOARD_URL + `update/${id}`, dataToSubmit, {headers});

        dispatch ({
            type: UPDATE_BOARD,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const deleteBoard = (id) => async dispatch => {
    try {
        const response = await axios.delete(BOARD_URL + `delete/${id}`, {headers});

        dispatch ({
            type: DELETE_BOARD,
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
        case BOARD_PAGE:
            return {
                ...state,
                boardPage: action.payload,
            }
        case GET_BOARD:
            return {
                ...state,
                board: action.payload,
            };
        case UPDATE_BOARD:
            return {
                ...state,
                board: action.payload,
            };
        case DELETE_BOARD:
            return {
                ...state,
                board: action.payload,
            }
        default:
            return state;
    };
};