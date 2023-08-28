import React from 'react'
import {FaBars, FaTimes} from 'react-icons/fa'

const NavBar = () => {
    return (
        <div className="flex justify-between items-center w-full h20 text-white bg-black fixed">
            {/*     fixed - sticks to the top
                    without fixed the bar will be invisible afer scrolling*/}
            <div>
                <h1 className="text-4xl">Jonathan</h1>
            </div>
        </div>
    );
}

export default NavBar;
