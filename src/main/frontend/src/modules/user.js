import axios from "axios";

const USER_URL = 'http://localhost:8080/';

// Inital State
const initalState = {
    user: null,
    isLoggedIn: false,
};

// Action
const REGISTER_USER = 'REGISTER_USER';
const LOGIN_USER = 'LOGIN_USER';
const LOGOUT_USER = 'LOGOUT_USER';

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
        console.log(response.data);

        localStorage.setItem('accessToken', response.data.accessToken);
        const token = localStorage.getItem('accessToken');
        
        console.log('accessToken:', token);

        dispatch ({
            type: LOGIN_USER,
            payload: response,
        });

        alert('로그인 성공');
    } catch (error) {
        console.error(error);

        alert('로그인 실패');
    }
};

export const logoutUser = () => dispatch => {
    dispatch ({
        type: LOGOUT_USER,
    });
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
                isLoggedIn: true,
            };
        case LOGOUT_USER:
            return {
                ...state,
                user: null,
                isLoggedIn: false,
            }
        default:
            return state;
    };
};