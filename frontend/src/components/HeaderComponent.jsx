import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header className="header">
                    <nav className= "navbar navbar-expand-md navbar-dark bg-dark"></nav>
                    <div>
                        <a href="#" className="navbar-brand">Client management app</a>
                    </div>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;