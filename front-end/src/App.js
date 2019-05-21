import React from 'react';
import logo from './logo.svg';
import './App.css';
import {createMuiTheme, CssBaseline} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import {makeStyles, withStyles} from "@material-ui/styles";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogActions from "@material-ui/core/DialogActions";

const theme = createMuiTheme();

const AppStyles = {
    textField: {
        margin: theme.spacing(2),
    }
};


class App extends React.Component {

    constructor(props) {
        super(props);
        const {classes} = props;
        this.classes = classes;
        this.state = {
            open: false,
            message: {
                title: "N/A",
                text: "N/A",
            }
        };

        this.default = "import java.util.function.Supplier;\n" +
            "class test implements Supplier<String> {\n" +
            "    @Override\n" +
            "    public String get() {\n" +
            "        return \"Something\";\n" +
            "    }\n" +
            "}";

        this.submitCode = this.submitCode.bind(this);
        this.closePopup = this.closePopup.bind(this);
    }

    submitCode() {
        const code = document.getElementById("code").value;

        if(code) {
            const url = new URL("http://localhost:5000/submit");
            url.searchParams.append("code", code);
            fetch(url).then(response => {
                // if (response.status === 200) {
                return Promise.all([response.status, response.text()]);
            }).then(promise => {
                this.setState({
                    message: {
                        title: promise[0],
                        text: promise[1],
                    },
                    open: true,
                    error: false,
                    helper: null
                });
                // }
            }).catch((error)=>{
                this.setState({
                    message: {
                        title: "Unknown Error while submitting code",
                        text: "Server is most likely down. Error: " + error,
                    },
                    open: true,
                    error: false,
                    helper: null
                });
            });
        } else {
            this.setState({error: true, helper: "Please insert your code here."})
        }
    }

    closePopup() {
        this.setState({open: false});
    }

    render() {
        return (
            <React.Fragment>
                <CssBaseline/>
                <Paper className="paper">
                    <TextField
                        id="code"
                        label="Paste Code Here"
                        multiline
                        fullWidth
                        rows={12}
                        rowsMax={48}
                        className={this.classes.textField}
                        margin="normal"
                        variant="outlined"
                        error={this.state.error}
                        helperText={this.state.helper}
                        defaultValue={this.default}
                    />

                    <Button variant='contained' onClick={this.submitCode}>
                        Submit
                    </Button>
                </Paper>

                <Dialog
                    open={this.state.open}
                    onClose={this.closePopup}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{this.state.message.title}</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            {this.state.message.text}
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.closePopup} color="primary" autoFocus>
                            Ok
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        );
    }
}

export default withStyles(AppStyles)(App);
