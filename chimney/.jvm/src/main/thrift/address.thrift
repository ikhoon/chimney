namespace java tutorial.thriftjava
#@namespace scala turorial.thriftscala

enum TPhoneType {
    MOBILE = 0;
    HOME = 1;
    WORK = 2;
}

struct TPhoneNumber {
    1: string number ;
    2: TPhoneType type;
}

struct TPerson {
    1: string tname;
    2: i32 tid;
    3: string temail;
}

struct TApple {
  1: required i32 a
  2: optional string b
}

struct TOrange {
  1: required bool a
}

union TFruit {
<<<<<<< Updated upstream
  1: TApple app
  2: TOrange ora
=======
  1: TOrange orange1
  2: TApple apple2
>>>>>>> Stashed changes
}