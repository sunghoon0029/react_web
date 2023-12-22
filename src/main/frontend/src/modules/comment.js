import axios from "axios";

const COMMENT_URL = 'http://localhost:8080/comment/';

// Inital State
const initalState = {
    comment: null,
    commentList: [],
};

// Action
const CREATE_COMMENT = 'CREATE_COMMENT';
const COMMENT_LIST = 'COMMENT_LIST';
const UPDATE_COMMENT = 'UPDATE_COMMENT';
const DELETE_COMMENT = 'DELETE_COMMENT';

const accessToken = localStorage.getItem('accessToken');

const headers = {
    Authorization: `Bearer ${accessToken}`
};

export const createComment = (id, dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(`http://localhost:8080/board/${id}/comment/save`, dataToSubmit, {headers});

        dispatch ({
            type: CREATE_COMMENT,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const commnetList = (id) => async dispatch => {
    try {
        const response = await axios.get(`http://localhost:8080/board/${id}/comment/`, {headers});

        dispatch ({
            type: COMMENT_LIST,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const updateComment = (id) => async dispatch => {
    try {
        const response = await axios.put(COMMENT_URL + `update/${id}`, {headers});

        dispatch ({
            type: UPDATE_COMMENT,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const deleteComment = (id) => async dispatch => {
    try {
        const response = await axios.delete(COMMENT_URL + `delete/${id}`, {headers});

        dispatch ({
            type: DELETE_COMMENT,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

// Reducer
export default function reducer(state = initalState, action) {
    switch (action.type) {
        case CREATE_COMMENT:
            return {
                ...state,
                comment: action.payload,
            };
        case COMMENT_LIST:
            return {
                ...state,
                commentList: action.payload,
            };
        case UPDATE_COMMENT:
            return {
                ...state,
                comment: action.payload,
            };
        case DELETE_COMMENT:
            return {
                ...state,
                comment: action.payload,
            };
        default:
            return state;
    };
};
