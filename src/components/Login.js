import { 
    Button, 
    Container, 
    Grid, 
    IconButton, 
    InputAdornment, 
    Paper, 
    TextField } from '@mui/material';
import React, { useState } from 'react';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {

    const navigate = useNavigate();

    const [ values, setValues] = useState({
        email: "",
        password: "",
        showPassword: false
    });


    const handleSubmit = (e) => {
        e.preventDefault();
        axios
            .post("http://localhost:8080/api/v1/auth/authenticate", {
                email: values.email,
                password: values.password
            })
            .then(res => {
                localStorage.setItem("token", res.data);
                navigate("/home");
            })
            .catch(err => console.error(err))
    }

    const handlePasswordVisibility = () => {
        setValues({...values, showPassword: !values.showPassword})
    }


    return (
        <div>
            <Container maxWidth="sm">
                <Grid 
                    spacing={2} 
                    justifyContent="center"
                    sx={{
                        marginTop: 20,
                      }}
                >
                    <Paper elevation={2} sx={{ padding: 5}}>
                        <form>
                            <Grid container spacing={2} direction="column">
                                <Grid item>
                                    <TextField 
                                        type="email" 
                                        fullWidth 
                                        label="Enter your email" 
                                        placeholder='Email Address'
                                        variant='outlined'
                                        required
                                        onChange={(e) => setValues({...values, email: e.target.value })}
                                    />
                                </Grid>

                                <Grid item>
                                    <TextField 
                                        type={ values.showPassword ? "text" : "password" }
                                        fullWidth 
                                        label="Enter your password" 
                                        placeholder='Password'
                                        variant='outlined'
                                        required
                                        onChange={(e) => setValues({...values, password: e.target.value })}
                                        InputProps={{
                                            endAdornment: (
                                                <InputAdornment position="end">
                                                    <IconButton 
                                                        onClick={handlePasswordVisibility}
                                                        aria-label="toggle password"
                                                        edge="end"
                                                    >
                                                        {values.showPassword ? (
                                                            <VisibilityOffIcon />
                                                        ) : (
                                                            <VisibilityIcon />
                                                        ) }
                                                        
                                                    </IconButton>
                                                </InputAdornment>
                                            )
                                        }}
                                    />
                                </Grid>

                                <Grid item>
                                    <Button variant='contained' fullWidth onClick={handleSubmit}>Sign In</Button> 
                                </Grid>
                            </Grid>
                        </form>
                    </Paper>
                </Grid>
            </Container>
        </div>
    );
};

export default Login;
