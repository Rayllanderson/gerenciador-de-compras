import React from 'react';
import {Switch} from 'react-router';
import Route from './Route';
import CategoryPage from "../pages/CategoryPage";
import ProductPage from "../pages/ProductPage";
import {InitialPage} from "../pages/InitialPage";
import {Account} from "../pages/account/Account";
import StatisticPage from "../pages/statistic";
import {HelpPage} from "../pages/help";
import NotFound from "../pages/notFound";
import {Redirect} from "react-router-dom";
import Home from "../pages/home/Home";
import AllProductPage from "../pages/AllProductPage";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={InitialPage} />
    <Route path="/register" exact component={InitialPage} />
    <Route path="/home" exact component={Home} hasNavBar isPrivate />
    <Route path="/categories" exact component={CategoryPage} isPrivate hasNavBar />
    <Route path="/categories/:id/products" exact component={ProductPage} isPrivate hasNavBar />
    <Route path="/all-products" exact component={AllProductPage} isPrivate hasNavBar />
    <Route path="/account" exact component={Account} isPrivate hasNavBar />
    <Route path="/statistics" exact component={StatisticPage} isPrivate hasNavBar />
    <Route path="/help" exact component={HelpPage} isPrivate hasNavBar />
    <Route path="/404" exact component={NotFound} hasNavBar isPrivate />
    <Redirect to="/404" />
  </Switch>
)

export default Routes;