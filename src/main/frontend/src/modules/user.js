import axios from "axios";

const USER_URL = 'http://localhost:8080/';

// Inital State
const initalState = {
    user: null,
};

// Action
const JOIN_USER = 'JOIN_USER';

export const joinUser = async (user) => {
    try {
        const res = await axios.post(USER_URL + 'join', user);

        return {
            type: JOIN_USER,
            payload: res.data,
        };

    } catch (err) {
        console.log(err);
    };
};

// Reducer
export default function reducer(state = initalState, action) {
    switch (action.type) {
        case JOIN_USER:
            return {
                ...state,
                user: action.payload,
            };
        default:
            return state;
    };
};