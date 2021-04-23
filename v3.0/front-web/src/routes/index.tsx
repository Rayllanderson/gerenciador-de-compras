import React from 'react';
import { Switch } from 'react-router';
import Route from './Route';
import LoginPage from "../pages/SignIn";

const Routes: React.FC = () => (
  <Switch>
    <Route path="/" exact component={LoginPage} />
  </Switch>
)

export default Routes;