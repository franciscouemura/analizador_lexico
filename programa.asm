global _main
	extern _printf
	extern _putchar
	extern _scanf
section .text
sum:
	push ebp
	push dword[@DSP + 4]
	mov ebp,esp
	mov dword[@DSP +4],ebp
	sub esp,0
	push dword [ebp + 16]
	push dword [ebp + 12]
	pop eax
	add dword [esp], eax
	mov eax, [esp]
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
	sub esp,8
	push 0
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	push 1
	push 1
	call sum
	add esp, 8
	push eax
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
