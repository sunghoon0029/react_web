import React from 'react';
import ReactDOM from 'react-dom/client';

import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';
import rootReducer from './modules/index'
import thunk from 'redux-thunk';

import App from './App';

const store = createStore(rootReducer, applyMiddleware(thunk));
const persistor = persistStore(store);

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
            <App />
        </PersistGate>
    </Provider>
);
