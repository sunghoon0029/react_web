import axios from "axios";

const COMMENT_URL = 'http://localhost:8080/comment/';

// Inital State
const initalState = {
    comment: null,
};

// Action
const CREATE_COMMENT = 'CREATE_COMMENT';

const accessToken = localStorage.getItem('accessToken');

const headers = {
    Authorization: `Bearer ${accessToken}`
};

export const createComment = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(COMMENT_URL + 'save', dataToSubmit, {headers});

        dispatch ({
            type: CREATE_COMMENT,
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
        default:
            return state;
    };
};
