import React from "react";
import {CyanButton, GreenButton, PrimaryButton, RedButton, YellowButton} from "../components/Buttons/style";

export default function Categories(){
    return (
        <div>
            <PrimaryButton className="btn" style={{fontWeight: 500}}>Submit</PrimaryButton>
            <RedButton className="btn">Hey...</RedButton>
            <CyanButton className="btn">Hey...</CyanButton>
            <YellowButton className="btn">Hey...</YellowButton>
            <GreenButton className="btn">Hey...</GreenButton>
        </div>
    );
}