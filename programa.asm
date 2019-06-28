global _main
	extern _printf
	extern _putchar
	extern _scanf
section .text
fat:
	push ebp
	push dword[@DSP + 4]
	mov ebp,esp
	mov dword[@DSP +4],ebp
	sub esp,8
	push 1
	mov eax, dword[@DSP+4]
	pop ebx
	mov dword[eax - 8], ebx
	push 2
	mov eax, dword[@DSP+4]
	pop ebx
	mov dword[eax - 12], ebx
	push dword [ebp - 8]
	push dword [ebp - 12]
	pop eax
	add dword [esp], eax
	add esp, 8
	mov ebp, esp
	pop dword[@DSP+4]
	pop ebp
	ret
_main:
	push ebp
	push dword[@DSP + 0]
	mov ebp,esp
	mov dword[@DSP +0],ebp
	sub esp,8
sub esp, 4
	push 2
	call fat
	add esp, 4
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	push dword [ebp - 4]
	push dword @INTEGER
	call _printf
	add esp, 8
	add esp, 4
	mov ebp, esp
	pop dword[@DSP+0]
	pop ebp
	ret
section .data
	@DSP times 4 db 0
	@INTEGER: db '%d' , 0
