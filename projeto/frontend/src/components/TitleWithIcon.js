import React from 'react';
import PropTypes from 'prop-types';
import "../styles/title-with-icon.css"

const TitleWithIcon = ({ icon, title }) => {
    return (

        <div className="container-title-icon">

            <i>{icon}</i>

            <h2>{title}</h2>

        </div>

    );
};

TitleWithIcon.propTypes = {
    icon: PropTypes.element.isRequired,
    title: PropTypes.string.isRequired,
};


export default TitleWithIcon;