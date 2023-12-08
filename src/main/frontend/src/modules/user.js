import axios from "axios";

const USER_URL = 'http://localhost:8080/';

// Inital State
const initalState = {
    user: null,
    isLoggedIn: false,
};

// Action
const LOCAL_STORAGE = 'LOCAL_STORAGE';
const REGISTER_USER = 'REGISTER_USER';
const LOGIN_USER = 'LOGIN_USER';
const LOGOUT_USER = 'LOGOUT_USER';
const GET_USER = 'GET_USER';

export const localStorageCheck = () => dispatch => {
    const token = localStorage.getItem('accessToken');
    console.log('accessToken:', token);

    dispatch ({
        type: LOCAL_STORAGE,
        payload: token,
    });
};

export const registerUser = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(USER_URL + 'join', dataToSubmit);

        dispatch ({
            type: REGISTER_USER,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

export const loginUser = (dataToSubmit) => async dispatch => {
    try {
        const response = await axios.post(USER_URL + 'login', dataToSubmit);
        console.log(response.data);

        const accessToken = response.data.accessToken;
        localStorage.setItem('accessToken', accessToken);

        // console.log(axios.defaults.headers.common['Authorization']);
        // axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;

        dispatch ({
            type: LOGIN_USER,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    }
};

export const logoutUser = () => dispatch => {

    localStorage.removeItem('accessToken');

    dispatch ({
        type: LOGOUT_USER,
    });
}

export const getUser = (id) => async dispatch => {
    try {
        const accessToken = localStorage.getItem('accessToken');
        const headers = {
            Authorization: `Bearer ${accessToken}`
        };

        const response = await axios.get(USER_URL + `member/${id}`);

        console.log(response);

        dispatch ({
            type: GET_USER,
            payload: response.data,
        });
    } catch (error) {
        console.error(error);
    };
};

// Reducer
export default function reducer(state = initalState, action) {
    switch (action.type) {
        case LOCAL_STORAGE:
            return {
                ...state,
                user: action.payload,
            };
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
        case GET_USER:
            return {
                ...state,
                user: action.payload,
            }
        default:
            return state;
    };
};