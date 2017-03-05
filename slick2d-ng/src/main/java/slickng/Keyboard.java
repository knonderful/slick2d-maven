package slickng;

public interface Keyboard {

  static final int KEY_ESCAPE = 0x01;
  static final int KEY_1 = 0x02;
  static final int KEY_2 = 0x03;
  static final int KEY_3 = 0x04;
  static final int KEY_4 = 0x05;
  static final int KEY_5 = 0x06;
  static final int KEY_6 = 0x07;
  static final int KEY_7 = 0x08;
  static final int KEY_8 = 0x09;
  static final int KEY_9 = 0x0A;
  static final int KEY_0 = 0x0B;
  /**
   * - on main keyboard
   */
  static final int KEY_MINUS = 0x0C;
  static final int KEY_EQUALS = 0x0D;
  /**
   * backspace
   */
  static final int KEY_BACK = 0x0E;
  static final int KEY_TAB = 0x0F;
  static final int KEY_Q = 0x10;
  static final int KEY_W = 0x11;
  static final int KEY_E = 0x12;
  static final int KEY_R = 0x13;
  static final int KEY_T = 0x14;
  static final int KEY_Y = 0x15;
  static final int KEY_U = 0x16;
  static final int KEY_I = 0x17;
  static final int KEY_O = 0x18;
  static final int KEY_P = 0x19;
  static final int KEY_LBRACKET = 0x1A;
  static final int KEY_RBRACKET = 0x1B;
  /**
   * Enter on main keyboard
   */
  static final int KEY_RETURN = 0x1C;
  static final int KEY_LCONTROL = 0x1D;
  static final int KEY_A = 0x1E;
  static final int KEY_S = 0x1F;
  static final int KEY_D = 0x20;
  static final int KEY_F = 0x21;
  static final int KEY_G = 0x22;
  static final int KEY_H = 0x23;
  static final int KEY_J = 0x24;
  static final int KEY_K = 0x25;
  static final int KEY_L = 0x26;
  static final int KEY_SEMICOLON = 0x27;
  static final int KEY_APOSTROPHE = 0x28;
  /**
   * accent grave
   */
  static final int KEY_GRAVE = 0x29;
  static final int KEY_LSHIFT = 0x2A;
  static final int KEY_BACKSLASH = 0x2B;
  static final int KEY_Z = 0x2C;
  static final int KEY_X = 0x2D;
  static final int KEY_C = 0x2E;
  static final int KEY_V = 0x2F;
  static final int KEY_B = 0x30;
  static final int KEY_N = 0x31;
  static final int KEY_M = 0x32;
  static final int KEY_COMMA = 0x33;
  /**
   * . on main keyboard
   */
  static final int KEY_PERIOD = 0x34;
  /**
   * / on main keyboard
   */
  static final int KEY_SLASH = 0x35;
  static final int KEY_RSHIFT = 0x36;
  /**
   * * on numeric keypad
   */
  static final int KEY_MULTIPLY = 0x37;
  /**
   * left Alt
   */
  static final int KEY_LMENU = 0x38;
  static final int KEY_SPACE = 0x39;
  static final int KEY_CAPITAL = 0x3A;
  static final int KEY_F1 = 0x3B;
  static final int KEY_F2 = 0x3C;
  static final int KEY_F3 = 0x3D;
  static final int KEY_F4 = 0x3E;
  static final int KEY_F5 = 0x3F;
  static final int KEY_F6 = 0x40;
  static final int KEY_F7 = 0x41;
  static final int KEY_F8 = 0x42;
  static final int KEY_F9 = 0x43;
  static final int KEY_F10 = 0x44;
  static final int KEY_NUMLOCK = 0x45;
  /**
   * Scroll Lock
   */
  static final int KEY_SCROLL = 0x46;
  static final int KEY_NUMPAD7 = 0x47;
  static final int KEY_NUMPAD8 = 0x48;
  static final int KEY_NUMPAD9 = 0x49;
  /**
   * - on numeric keypad
   */
  static final int KEY_SUBTRACT = 0x4A;
  static final int KEY_NUMPAD4 = 0x4B;
  static final int KEY_NUMPAD5 = 0x4C;
  static final int KEY_NUMPAD6 = 0x4D;
  /**
   * + on numeric keypad
   */
  static final int KEY_ADD = 0x4E;
  static final int KEY_NUMPAD1 = 0x4F;
  static final int KEY_NUMPAD2 = 0x50;
  static final int KEY_NUMPAD3 = 0x51;
  static final int KEY_NUMPAD0 = 0x52;
  /**
   * . on numeric keypad
   */
  static final int KEY_DECIMAL = 0x53;
  static final int KEY_F11 = 0x57;
  static final int KEY_F12 = 0x58;
  /**
   * (NEC PC98)
   */
  static final int KEY_F13 = 0x64;
  /**
   * (NEC PC98)
   */
  static final int KEY_F14 = 0x65;
  /**
   * (NEC PC98)
   */
  static final int KEY_F15 = 0x66;
  /**
   * Extended Function keys - (Mac)
   */
  static final int KEY_F16 = 0x67;
  static final int KEY_F17 = 0x68;
  static final int KEY_F18 = 0x69;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_KANA = 0x70;
  /**
   * Extended Function keys - (Mac)
   */
  static final int KEY_F19 = 0x71;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_CONVERT = 0x79;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_NOCONVERT = 0x7B;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_YEN = 0x7D;
  /**
   * = on numeric keypad (NEC PC98)
   */
  static final int KEY_NUMPADEQUALS = 0x8D;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_CIRCUMFLEX = 0x90;
  /**
   * (NEC PC98)
   */
  static final int KEY_AT = 0x91;
  /**
   * (NEC PC98)
   */
  static final int KEY_COLON = 0x92;
  /**
   * (NEC PC98)
   */
  static final int KEY_UNDERLINE = 0x93;
  /**
   * (Japanese keyboard)
   */
  static final int KEY_KANJI = 0x94;
  /**
   * (NEC PC98)
   */
  static final int KEY_STOP = 0x95;
  /**
   * (Japan AX)
   */
  static final int KEY_AX = 0x96;
  /**
   * (J3100)
   */
  static final int KEY_UNLABELED = 0x97;
  /**
   * Enter on numeric keypad
   */
  static final int KEY_NUMPADENTER = 0x9C;
  static final int KEY_RCONTROL = 0x9D;
  /**
   * Section symbol (Mac)
   */
  static final int KEY_SECTION = 0xA7;
  /**
   * , on numeric keypad (NEC PC98)
   */
  static final int KEY_NUMPADCOMMA = 0xB3;
  /**
   * / on numeric keypad
   */
  static final int KEY_DIVIDE = 0xB5;
  static final int KEY_SYSRQ = 0xB7;
  /**
   * right Alt
   */
  static final int KEY_RMENU = 0xB8;
  /**
   * Function (Mac)
   */
  static final int KEY_FUNCTION = 0xC4;
  /**
   * Pause
   */
  static final int KEY_PAUSE = 0xC5;
  /**
   * Home on arrow keypad
   */
  static final int KEY_HOME = 0xC7;
  /**
   * UpArrow on arrow keypad
   */
  static final int KEY_UP = 0xC8;
  /**
   * PgUp on arrow keypad
   */
  static final int KEY_PRIOR = 0xC9;
  /**
   * LeftArrow on arrow keypad
   */
  static final int KEY_LEFT = 0xCB;
  /**
   * RightArrow on arrow keypad
   */
  static final int KEY_RIGHT = 0xCD;
  /**
   * End on arrow keypad
   */
  static final int KEY_END = 0xCF;
  /**
   * DownArrow on arrow keypad
   */
  static final int KEY_DOWN = 0xD0;
  /**
   * PgDn on arrow keypad
   */
  static final int KEY_NEXT = 0xD1;
  /**
   * Insert on arrow keypad
   */
  static final int KEY_INSERT = 0xD2;
  /**
   * Delete on arrow keypad
   */
  static final int KEY_DELETE = 0xD3;
  /**
   * Clear key (Mac)
   */
  static final int KEY_CLEAR = 0xDA;
  /**
   * Left Windows/Option key
   */
  static final int KEY_LMETA = 0xDB;
  /**
   * Right Windows/Option key
   */
  static final int KEY_RMETA = 0xDC;
  /**
   * AppMenu key
   */
  static final int KEY_APPS = 0xDD;
  static final int KEY_POWER = 0xDE;
  static final int KEY_SLEEP = 0xDF;

  /**
   * Retrieves the key-pressed status for the specified key code.
   *
   * @param keyCode The key code.
   * @return {@code true} if the key is pressed, otherwise {@code false}.
   */
  boolean isPressed(int keyCode);
}
