import React, {Component} from 'react';
import ClientService from "../services/ClientService";

class ListClientComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            clients: []
        }
        this.addClient = this.addClient.bind(this);
        this.editClient = this.editClient.bind(this);
        this.deleteClient = this.deleteClient.bind(this);
        this.viewClient = this.viewClient.bind(this);

    }

    editClient(id) {
        this.props.history.push(`/update-client/${id}`);

    }

    deleteClient(id){
        ClientService.deleteClient(id).then(res => {
            this.setState({client: this.state.clients.filter(client => client.id !== id)});
        });
    }

    componentDidMount() {
        ClientService.getClients().then(res => {
            this.setState({clients: res.data});
        });
        this.props.history.push('/clients');
    }

    addClient(){
        this.props.history.push('/add-client')
    }

    viewClient(id){
        this.props.history.push(`/view-client/${id}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Client List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addClient}>Add client</button>
                </div>
                <div className = "row">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Client First Name</th>
                                <th>Client Last Name</th>
                                <th>Client Email Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.clients.map(client =>
                                    <tr key={client.id}>
                                        <td>{client.name}</td>
                                        <td>{client.lastname}</td>
                                        <td>{client.email}</td>
                                        <td>
                                            <button className="btn btn-info" onClick={ () => {
                                                this.editClient(client.id)
                                            }}>Update</button>
                                            <button style={{marginLeft: "10px"}} className="btn btn-danger" onClick={ () => {
                                                this.deleteClient(client.id); window.location.reload(false);
                                            }}>Delete</button>
                                            <button style={{marginLeft: "10px"}} className="btn btn-success" onClick={ () => {
                                                this.viewClient(client.id)
                                            }}>View</button>
                                        </td>

                                    </tr>)
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ListClientComponent;