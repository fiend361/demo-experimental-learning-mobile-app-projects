

class ValidationMixin {
  String validateEmail(String value) {
    if (!value.contains('@')) {
      return 'please enter a valid email';
    }
    return null;
  }

  String validatePassword(String value) {
    if (value.length < 8) {
      return 'enter a longer password';
    }
    return null;
  }

}