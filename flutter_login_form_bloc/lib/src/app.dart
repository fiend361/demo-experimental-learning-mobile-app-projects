// comments

import 'package:flutter/material.dart';
import 'screens/login_screen.dart';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'login with bloc',
      home: Scaffold(
        body: LoginScreen(),
      ),
    );
  }
}
