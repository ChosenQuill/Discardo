import React from 'react';
import './App.css';
import {createMuiTheme, CssBaseline} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogActions from "@material-ui/core/DialogActions";
import {Link, Route, Switch, withRouter} from 'react-router-dom';
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/styles";
import logo from './logo.png';
import Divider from "@material-ui/core/Divider";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";

const theme = createMuiTheme();

const BarStyles = makeStyles({
    title: {
        flexGrow: 1,
    },
});

function Bar(props) {
    const classes = BarStyles();
    return (
        <AppBar position="sticky">
            <Toolbar>
                <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="Logo"
                            href='https://www.suredroid.com'>
                    <img src={logo} style={{width: '32px'}}/>
                </IconButton>
                <Typography variant="h6" className={classes.title}>
                    Discardo Bot Competition
                </Typography>
                <nav>
                    <Button color={props.loc === "/" ? "secondary" : "default"} variant='contained' component={Link}
                            style={{marginRight: '12px'}} to='/'>Info</Button>
                    <Button color={props.loc === "/leaderboard" ? "secondary" : "default"} variant='contained'
                            style={{marginRight: '12px'}} component={Link} to='/leaderboard'>Leaderboard</Button>
                    <Button color={props.loc === "/submit" ? "secondary" : "default"} variant='contained'
                            component={Link} to='/submit'>Submit</Button>
                </nav>
            </Toolbar>
        </AppBar>
    )

}

class LeaderBoard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {board: null};
        fetch('http://localhost:5000/leaderboard')
            .then(res => res.json())
            .then(board => this.setState({board: [...board]}))
            .then(res => console.log(this.state.board))
            .catch(err => console.log(err));
    }

    render() {
        return (
            <Paper className="paper">
                <Box my={1} mx={2}>

                    <Typography variant='h4'>
                        Leaderboard
                    </Typography>

                </Box>
                {(this.state.board) &&
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell align="right">Average Turns</TableCell>
                            <TableCell align="right">Tries</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            this.state.board.map((user, pos) => (
                                <TableRow key={user.name}>
                                    <TableCell component='th' scope='row'>
                                        {(pos + 1) + '. '}<b>{user.name}</b>
                                    </TableCell>

                                    <TableCell align='right'>{user.bestAverage}</TableCell>
                                    <TableCell align='right'>{user.tries}</TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
                }
                <Box mt={2} mb={1} mx={2}>
                    <Typography>
                        Want to get on here and become the best? Submit your code <Link to='/submit'>here</Link> and learn how to join <Link to='/'>here</Link>.
                    </Typography>
                </Box>
            </Paper>
        );
    }
}

function Info() {
    return (
        <Paper className="paper">
            <Box my={1} mx={2}>
                <Typography variant='h4'>
                    Welcome to the Discardo Bot competition!
                </Typography>
                <Typography variant='subtitle1' gutterBottom>
                    All your questions are answered below.
                </Typography>
                <Box my={1} mr={12}>
                    <Divider/>
                </Box>
                <Typography variant='h6'>
                    What is this place?
                </Typography>
                <Typography paragraph>
                    This place is a fun (Java) coding competition to see who can create the best Discardo bot (see
                    below). Try to get your code to automatically beat the game in the least amount of turns.<br/>
                    You can view the <Link to='/leaderboard'>leaderboard</Link> to see everyone's position.
                </Typography>
                <Typography variant='h6'>
                    What is Discardo?
                </Typography>
                <Typography paragraph>
                    Discardo is <b>the most popular</b> single player card game that everyone talks about. It includes
                    an infinite deck of numbered cards. Each card has a single number from 1 to 9 (inclusive), each
                    equally likely to occur.<br/>
                    The player is dealt a hand consisting of a predetermined number of cards. For example, the player
                    might be dealt the hand [8, 4, 6].<br/>
                    On each turn, the player draws a numbered card from the deck. For example, the number 9. The player
                    may keep the 9 and discard one of the cards from their hand. Alternatively, the player may discard
                    the 9 they drew, leaving their hand unchanged. For example, the player may choose to discard the 4,
                    so that they now hold [8, 9, 6].<br/>
                    Play continues in this manner until the player's hand meets a predetermined goal, such as making a
                    run (sometimes called a straight). Examples of runs include [1, 2, 3], [9, 5, 8, 6, 7] and [5,
                    4].<br/>
                    The object of the game is to reach the goal in the fewest number of turns.
                </Typography>
                <Typography>
                    <b>It is recommended you download and try to complete the <a href='/Discardo Project.zip' download>Discardo
                        Project</a> beforehand to gain a deeper understanding of how it works. </b> <br/>
                </Typography>
            </Box>
        </Paper>
    );
}

const code =
    "import com.suredroid.discardo.Test;\n" +
    "\n" +
    "public class NewTest implements Test {\n" +
    "\n" +
    "    @Override\n" +
    "    public String doSomething() {\n" +
    "        return \"Value\";\n" +
    "    }\n" +
    "}\n";


function Submit(props) {
    return (
        <Paper className="paper">
            <Box my={1} mx={2}>
                <TextField
                    id='name'
                    label='Name'
                    helperText={props.helper2}
                    error={props.error2}
                />
                <Box mb={1}/>
                <TextField
                    id="code"
                    label="Paste Code Here"
                    multiline
                    fullWidth
                    rows={12}
                    rowsMax={48}
                    margin="normal"
                    variant="outlined"
                    error={props.error}
                    helperText={props.helper}
                    defaultValue={code}
                />
                <Box mb={1}/>
                <Button variant='contained' onClick={props.submitCode}>
                    Submit
                </Button>
            </Box>
        </Paper>
    );
};


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            open: false,
            message: {
                title: "N/A",
                text: "N/A",
            },
        };
        this.current = 0;
        this.submitCode = this.submitCode.bind(this);
        this.closePopup = this.closePopup.bind(this);
        this.props = props;
    }

    submitCode() {
        const code = document.getElementById("code").value;
        const name = document.getElementById("name").value;

        if (code && name) {
            const url = new URL("http://localhost:5000/submit");
            url.searchParams.append("code", code);
            url.searchParams.append("name", name);
            fetch(url).then(response =>
                Promise.all([response.status, response.text()])
            ).then(promise => {
                console.log(promise[0]);
                let text = promise[1].split('\n').map((item, i) => {
                        return <span key={i} className='line'>{item}</span>;
                    }),
                    status;
                switch (promise[0]) {
                    case 470:
                        status = "Oh No. Something went wrong on your side.";
                        break;
                    case 400:
                        status = "Oh No. Your code didn't compile.";
                        break;
                    case 500:
                        status = "Oh No. The server encountered an error.";
                        break;
                    case 200:
                        status = "Success. The code is being processed.";
                        let url = new URL("http://localhost:5000/status");
                        url.searchParams.append("name", name);
                        fetch(url).then(response => Promise.all([response.status, response.json()])
                            .then(promise => {
                                let body = promise[1];
                                console.log(promise[0]);
                                if (promise[0] !== 200) {
                                    body.title = "Oh no, an unknown error!";
                                    body.text = "The server is most likely down. Report this error to support@suredroid.com and to your teacher.";
                                }
                                this.hmessage = {
                                    title: body.title,
                                    text: body.text,
                                };
                                this.pending = true;
                                this.closePopup();
                            }).catch(err => {
                                this.hmessage = {
                                    title: "Oh no, an unknown error!",
                                    text: "The server is most likely down. Report the error below to support@suredroid.com and to your teacher.\n" + err,
                                };
                                this.pending = true;
                                this.closePopup();
                            }));

                        break;
                    default:
                        status = "Oh No. We encountered an unknown error.";
                        break;
                }

                if (text.length === 0)
                    text = "No error message. Try again later and if issue persists, report to support@suredroid.com or/and your teacher.";

                this.setState({
                    message: {
                        title: status,
                        text: text,
                    },
                    open: true,
                    error: false,
                    error2: false,
                    helper2: null,
                    helper: null,
                });
                // }
            }).catch((error) => {
                this.setState({
                    message: {
                        title: "Unknown Error while submitting code",
                        text: "Server is most likely down. Error: " + error,
                    },
                    open: true,
                    error: false,
                    error2: false,
                    helper2: null,
                    helper: null
                });
            });
        } else {
            if (!code)
                this.setState({error: true, helper: "Please insert your code here."});
            if (!name)
                this.setState({error2: true, helper2: "Please insert your name here."});

        }
    }

    closePopup() {
        if (this.state.open)
            this.setState({open: false});
        if (this.pending) {
            this.pending = false;
            setTimeout(() => {
                this.setState({
                    message: {
                        title: this.hmessage.title,
                        text: this.hmessage.text,
                    },
                    open: true,
                });
            }, 400);
        }
    }

    render() {
        return (
            <React.Fragment>
                <CssBaseline/>
                <Bar loc={this.props.location.pathname}/>
                <Switch>
                    <Route exact path='/' component={Info}/>
                    <Route exact path='/leaderboard' component={LeaderBoard}/>
                    <Route exact path='/submit'
                           render={(props) => <Submit {...props} {...this.state} submitCode={this.submitCode}/>}/>
                </Switch>

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


export default withRouter(App);
