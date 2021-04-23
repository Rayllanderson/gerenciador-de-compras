import React from 'react';
import { Switch } from 'react-router';
import Route from './Route';
import LoginPage from "../pages/SignIn";
import CategoryPage from "../pages/categoryPage";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={LoginPage} />
    <Route path="/categories" exact component={CategoryPage} isPrivate hasNavBar />
  </Switch>
)

export default Routes;