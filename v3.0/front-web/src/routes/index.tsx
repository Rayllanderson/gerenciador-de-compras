import React from 'react';
import {Switch} from 'react-router';
import Route from './Route';
import CategoryPage from "../pages/CategoryPage";
import ProductPage from "../pages/ProductPage";
import {InitialPage} from "../pages/InitialPage";
import {Account} from "../pages/Account";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={InitialPage} />
    <Route path="/register" exact component={InitialPage} />
    <Route path="/categories" exact component={CategoryPage} isPrivate hasNavBar />
    <Route path="/categories/:id/products" exact component={ProductPage} isPrivate hasNavBar />
    <Route path="/account" exact component={Account} isPrivate hasNavBar />
  </Switch>
)

export default Routes;