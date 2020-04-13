how to compile and run WASM on Ubuntu Linux:
1. install WASM compiler:
    git clone https://github.com/emscripten-core/emsdk.git
    cd emsdk
    ./emsdk install latest
    ./emsdk activate latest
    source ./emsdk_env.sh
2. compile C code into WASM, HTML, and JavaScript:
    emcc filename.c -s WASM=1 -o filename.html
3. use built-in HTTP server:
    emrun --no_browser --port 8080 filename.html
4. run WASM:
    open http://0.0.0.0:8080/filename.html with a web browser
