import React, { useEffect } from "react";
import Layout from "../components/layout/Layout";
import { useDispatch } from "react-redux";
import { localStorageCheck } from "../modules/user";

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