import React from 'react';
import { Switch } from 'react-router';
import Route from './Route';
import LoginPage from "../pages/SignIn";
import CategoryPage from "../pages/categoryPage";
import ProductPage from "../pages/productPage";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={LoginPage} />
    <Route path="/categories" exact component={CategoryPage} isPrivate hasNavBar />
    <Route path="/products" exact component={ProductPage} isPrivate hasNavBar />
  </Switch>
)

export default Routes;