global _main
	extern _printf
	extern _putchar
	extern _scanf
section .text
p:
	push ebp
	push dword[@DSP + 4]
	mov ebp,esp
	mov dword[@DSP +4],ebp
	sub esp,0
	push 10
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	add esp, 0
	mov ebp, esp
	pop dword[@DSP+4]
	pop ebp
	ret
_main:
	push ebp
	push dword[@DSP + 0]
	mov ebp,esp
	mov dword[@DSP +0],ebp
	sub esp,4
	call p
	add esp, 0
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
