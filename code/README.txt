how to generate and run WebAssembly on Ubuntu Linux terminal:
1. install Emscripten SDK:
    mkdir .../foldername (make the directory where Emscripten SDK will be downloaded)
    cd .../foldername (navigate to the directory where Emscripten SDK will be downloaded)
    git clone https://github.com/emscripten-core/emsdk.git
    cd emsdk (everytime terminal is reset)
    ./emsdk install latest (whenever terminal is reset)
    ./emsdk activate latest (whenever terminal is reset)
    source ./emsdk_env.sh (whenever terminal is reset)
2. compile C programs with Emscripten SDK:
    cd .. (navigate out of the directory where Emscripten SDK was downloaded)
    mkdir .../foldername (make the directory where the WebAssembly will be generated)
    cd .../foldername (navigate to the directory where the WebAssembly will be generated)
    nano filename.c
    write valid C code
    Ctrl+O (save)
    Ctrl+X (exit)
    emcc filename.c -s WASM=1 -o filename.html --emrun
3. run in Mozilla Firefox (Mozilla developed WebAssembly):
    emrun filename.html firefox
how to view or directly edit WebAssembly code in Ubuntu Linux terminal:
1. install WABT (WebAssembly Binary Toolkit):
    cd .. (navigate out of the directory where the WebAssembly was generated)
    mkdir .../foldername (make the directory where WABT will be downloaded)
    cd .../foldername (navigate to the directory where WABT will be downloaded)
    git clone --recursive https://github.com/WebAssembly/wabt
    cd wabt
    sudo apt install cmake
    mkdir build
    cd build
    cmake ..
    cmake --build .
2. convert WASM to WAT:
    cd .. (navigate out of the directory where WABT was downloaded)
    cd .../foldername (navigate to the directory where the WebAssembly was generated)
    .../wabt/wasm2wat filename.wasm -o filename.wat
3. open WAT:
    nano filename.wat
    view or directly edit WebAssembly code
    Ctrl+O (save)
    Ctrl+X (exit)
4. if you directly edited the WebAssembly code, convert WAT to WASM (this will overwrite):
    .../wabt/wat2wasm filename.wat -o filename.wasm
    emrun filename.html firefox (to see changes reflected)
