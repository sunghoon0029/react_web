import React, { useEffect } from "react";
import { localStorageCheck } from "../modules/user";

import Layout from "../components/layout/Layout";
import { useDispatch } from "react-redux";

const Home = () => {

    const dispatch = useDispatch();

    const localStorage = () => {
        dispatch(localStorageCheck());
    };

    useEffect(() => {
        localStorage();
    });

    return (
        <Layout>
            <div>
                <h2>Home</h2>
            </div>
        </Layout>
    );
};

export default Home;