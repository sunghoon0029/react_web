// Inital State
const initalState = {
    count: 0
};

// Action
const INCREASE = 'counter/INCREASE';
const DECREASE = 'counter/DECREASE';

export const increase = () => {
    return {
        type: INCREASE
    };
};

export const decrease = () => {
    return {
        type: DECREASE
    };
};

// Reducer
export default function counter(state = initalState, action) {
    switch (action.type) {
        case INCREASE:
            return {
                ...state,
                count: state.count + 1
            };
        case DECREASE:
            return {
                ...state,
                count: state.number - 1
            };
        default:
            return state;
    };
};