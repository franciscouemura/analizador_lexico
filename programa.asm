global _main
	extern _printf
	extern _putchar
	extern _scanf
section .text
_main:
	push ebp
	push dword[@DSP + 0]
	mov ebp,esp
	mov dword[@DSP +0],ebp
	sub esp,12
	push 0
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
rotuloRepeat0:
	push dword [ebp - 4]
	push dword @INTEGER
	call _printf
	add esp, 8
	push @string1
	call _printf
	add esp, 4
	push dword [ebp - 4]
	push 1
	pop eax
	add dword [esp], eax
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	push dword [ebp - 4]
	push 3
	pop eax
	cmp dword [esp], eax
	jne rotuloFalse2
	mov dword [esp], 1
	jmp rotuloFim1
rotuloFalse2:
	mov dword [esp], 0
rotuloFim1:
	pop eax
	cmp eax, 0
	je rotuloRepeat0
	add esp, 12
	mov esp,ebp
	pop dword[@DSP+8]
	pop ebp
	ret
section .data
	@DSP times 4 db 0
	@INTEGER: db '%d' , 0
	@string1 db '' , 10, 0
