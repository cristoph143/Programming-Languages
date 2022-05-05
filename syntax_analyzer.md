```tefcha
main
start
idx = 0;
if data_types(tokens, idx)
    tmp = identifier(tokens, idx);
    idx += tmp.length();
    String[] sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
    if sub_tokens.length < 2
        isTerminator(tokens, tokens.length - 1);
    dec(sub_tokens, 0, tmp);
    return tokens;
else
    isTerminator(tokens, tokens.length - 1);
end
```
