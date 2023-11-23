// Inital State
const initalState = {
    user: {},
}

// Action
const SET_USER = 'SET_USER';

export const getToken = data => ({ type: SET_USER, data });

// Reducer
export default function user(state = initalState, action) {
    switch(action.type) {
        case SET_USER:
            return {
                ...state,
                user: action.data
            };
        default:
            return state;
    };
};