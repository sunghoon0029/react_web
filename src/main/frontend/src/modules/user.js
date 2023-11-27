import axios from "axios";

const USER_URL = 'http://localhost:8080/';

// Inital State
const initalState = {
    user: null,
};

// Action
const REGISTER_USER = 'REGISTER_USER';
const LOGIN_USER = 'LOGIN_USER';

export const registerUser = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(USER_URL + 'join', dataToSubmit);

        dispatch ({
            type: REGISTER_USER,
            payload: response,
        });
    } catch (error) {
        console.error(error);
    };
};

export const loginUser = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(USER_URL + 'login', dataToSubmit);

        dispatch ({
            type: LOGIN_USER,
            payload: response,
        });
    } catch (error) {
        console.error(error);
    }
}

// Reducer
export default function reducer(state = initalState, action) {
    switch (action.type) {
        case REGISTER_USER:
            return {
                ...state,
                user: action.payload,
            };
        case LOGIN_USER:
            return {
                ...state,
                user: action.payload,
            };
        default:
            return state;
    };
};