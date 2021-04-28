import React from 'react';
import {Switch} from 'react-router';
import Route from './Route';
import CategoryPage from "../pages/categoryPage";
import ProductPage from "../pages/productPage";
import {InitialPage} from "../pages/InitialPage";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={InitialPage} />
    <Route path="/register" exact component={InitialPage} />
    <Route path="/categories" exact component={CategoryPage} isPrivate hasNavBar />
    <Route path="/categories/:id/products" exact component={ProductPage} isPrivate hasNavBar />
  </Switch>
)

export default Routes;