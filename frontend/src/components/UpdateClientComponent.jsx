import React, {Component} from 'react';
import ClientService from "../services/ClientService";
import bcrypt from 'bcryptjs';

class UpdateClientComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            name: '',
            lastname: '',
            password: '',
            email: ''
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.updateClient = this.updateClient.bind(this);
    }

    componentDidMount() {
        ClientService.getClientById(this.state.id).then(res => {
            let client = res.data;
            this.setState({
                name: client.name,
                lastname: client.lastname,
                password: client.password,
                email: client.email
            })


        });
    }

    changeNameHandler(event){
        this.setState({name: event.target.value});
    }
    changeLastNameHandler(event){
        this.setState({lastname: event.target.value});
    }
    changeEmailHandler(event){
        this.setState({email: event.target.value});
    }
    changePasswordHandler(event){
        this.setState({password: event.target.value});
    }

    encodePassword(password){
        return bcrypt.hashSync(password, 8);
    }


    updateClient(event){
        event.preventDefault();
        let client = {name: this.state.name, lastname: this.state.lastname, password: this.state.password, email: this.state.email, status: 'Verfied'};
        console.log(JSON.stringify(client));
        ClientService.updateClient(client, this.state.id).then(res =>{
            this.props.history.push('/clients');
        });

    }

    cancel(){
        this.props.history.push('/clients');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col=md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update client</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>First Name: </label>
                                        <input placeholder="First Name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changeNameHandler}/>
                                        <label>Last Name: </label>
                                        <input placeholder="Last Name" name="lastname" className="form-control"
                                               value={this.state.lastname} onChange={this.changeLastNameHandler}/>
                                        <label>Password: </label>
                                        <input placeholder="password" name="password" className="form-control"
                                               value={this.state.password} onChange={this.changePasswordHandler}/>
                                        <label>Email: </label>
                                        <input placeholder="Email" name="email" className="form-control"
                                               value={this.state.email} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateClient}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdateClientComponent;