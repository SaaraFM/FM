from screen import draw_line, draw_title, change_cursor_pos, set_background_and_font_default_color, set_background_and_font_highlight_color
from time import sleep
import keyboard


"""
name: draw_option
kind: function
arguments: 
    - option: texto da opção do menu
    - menu_width: largura total do menu
    - x: posição horizontal do menu
    - y: posição vertical do menu
return: quantidade de linhas renderizadas
description: Renderiza 1 opção de um menu. Exemplo: | Opção 1 |
"""
def draw_option(option, menu_width, x=0, y=0):
    separators_count = 2
    empty_spaces_count = 2
    option_padding = " "*(menu_width - len(option) - separators_count - empty_spaces_count)
    change_cursor_pos(x, y)
    print(f"| {option}{option_padding} |")
    printed_lines = 1
    return printed_lines

##########################################################################################

"""
name: draw_menu
kind: function
arguments: 
    - title: texto do título do menu
    - options: lista com nome das opções do menu 
    - heigth: altura total do menu
    - width: largura total do menu
    - x: posição horizontal do menu
    - y: posição vertical do menu
return: quantidade de linhas renderizadas
description: Renderiza um menu contendo todas as suas opções, sendo que a opção selecionada é renderizada com fundo branco e letra preta.
"""
def draw_menu(title, options, selected_option, height, width, x=0, y=0):
    set_background_and_font_default_color
    printed_lines = draw_title(title, width, x, y)
    for option in options:
        set_background_and_font_default_color()
        if (option == selected_option):
            set_background_and_font_highlight_color()
        printed_lines += draw_option(option, width, x, y + printed_lines)
    set_background_and_font_default_color()
    for _ in range(height - len(options)):
        printed_lines += draw_option("", width, x, y + printed_lines)
    set_background_and_font_default_color()
    printed_lines += draw_line(width, x, y + printed_lines)
    return printed_lines

##########################################################################################

"""
name: execute_menu
kind: function
arguments: 
    - title: texto do título do menu
    - options: lista com nome das opções do menu
    - selected_option: opção inicialmente selecionada
    - heigth: altura total do menu
    - width: largura total do menu
    - x: posição horizontal do menu
    - y: posição vertical do menu
return: o nome da opção selecionada pelo usuário
description: Renderiza o menu e depois entra em um loop que permite ao usuário selecionar uma opção usando as setas do teclado.
"""
def execute_menu(title, options, selected_option, height, width, x=0, y=0):
    running = True
    if (selected_option in options):
        current_option = options.index(selected_option)
    else:
        current_option = 0
    draw_menu(title, options, selected_option, height, width, x, y)
    while running:                
        if keyboard.is_pressed("down arrow"):
            current_option = len(options)-1 if current_option + 1 >= len(options) - 1 else current_option + 1
        if keyboard.is_pressed("up arrow"):
            current_option = 0 if current_option - 1 <= 0 else current_option - 1
        if keyboard.is_pressed("enter"):
            running = False
        
        draw_menu(title, options, options[current_option], height, width, x, y)
        
        sleep(0.05)

    return options[current_option]

__all__ = ['execute_menu', 'draw_menu']