import React, {Component} from 'react';
import ClientService from "../services/ClientService";

class ViewClientComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            client: {}
        }
    }

    componentDidMount() {
        ClientService.getClientById(this.state.id).then(res => {
            this.setState({client: res.data});
        });
    }

    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center">View Client Details</h3>
                    <div className="card-body">
                        <div className="row">
                            <label>Client First Name: </label>
                            <div>{this.state.client.name}</div>
                        </div>
                        <div className="row">
                            <label>Client Last Name: </label>
                            <div>{this.state.client.lastname}</div>
                        </div>
                        <div className="row">
                            <label>Client Email: </label>
                            <div>{this.state.client.email}</div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

export default ViewClientComponent;