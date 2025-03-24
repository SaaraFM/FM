"""
name: change_cursor_pos
kind: function
arguments: 
    - x
    - y
return: void
description: Usa caracteres de escape pra mudar a posição do cursor no terminal
"""
def change_cursor_pos(x, y):
    print(f"\033[{y};{x}H", end="")

##########################################################################################

"""
name: set_background_and_font_default_color
kind: function
arguments: 
    - none
return: void
description: Usa caracteres de escape pra mudar a cor do fundo para preto e a cor da fonte para branco
"""
def set_background_and_font_default_color():
    print("\033[0;40;37m", end="")

##########################################################################################

"""
name: set_background_and_font_highlight_color
kind: function
arguments: 
    - none
return: void
description: Usa caracteres de escape pra mudar a cor do fundo para branco e a cor da fonte para preto
"""
def set_background_and_font_highlight_color():
    print("\033[1;30;47m", end="")

##########################################################################################

"""
name: draw_line
kind: function
arguments:
    - line_size
    - x
    - y
return: a quantidade de linhas renderizadas
description: Renderiza uma linha que começa e termina com "+" e é recheada com "-"
"""
def draw_line(line_size, x=0, y=0):
    change_cursor_pos(x,y)
    print("+", end="")
    print("-"*(line_size-2), end="")
    print("+", end="")
    lines_printed = 1
    return lines_printed

##########################################################################################

"""
name: draw_title
kind: function
arguments: 
    - title
    - width
    - x
    - y
return: a quantidade de linhas renderizadas
description: Renderiza um retangulo usando caracteres e dentro deste retangulo redenriza um texto.
"""
def draw_title(title, width, x=0, y=0):
    separators_count = 2
    empty_spaces_count = 2
    padding = " "*(width - len(title) - separators_count - empty_spaces_count)
    lines_printed = draw_line(width, x, y)
    print(f"\033[{y + lines_printed};{x}H", end="")
    print(f"| {title}{padding} |", end="")
    lines_printed += 1
    lines_printed += draw_line(width, x, y + lines_printed)
    return lines_printed

##########################################################################################

"""
name: draw_footer
kind: function
arguments: 
    - text
    - width
    - x
    - y
return: a quantidade de linhas renderizadas
description: Renderiza um retangulo usando caracteres e dentro deste retangulo redenriza um texto.
"""
def draw_footer(text, width, x=0, y=0):
    separators_count = 2
    empty_spaces_count = 2
    padding = " "*(width - len(text) - separators_count - empty_spaces_count)
    print(f"\033[{y};{x}H", end="")
    print(f"| {text}{padding} |", end="")
    lines_printed = 1
    lines_printed += draw_line(width, x, y + lines_printed)
    return lines_printed

##########################################################################################

"""
name: draw_screen
kind: function
arguments: 
    - title
    - footer
    - lines
    - height
    - width
    - x, 
    - y
return: a quantidade de linhas renderizadas
description: Renderiza um cabeçaho, um rodapé e entre os dois, renderiza linhas de texto
"""
def draw_screen(title, footer, lines, height, width, x=0, y=0):
    print("\033[0;40;37m", end="")
    lines_printed = draw_title(title, width, x, y)
    separators_count = 2
    empty_spaces_count = 2
    for i in range(height):
        line_content = lines[i] if i < len(lines) else ""
        line_padding_count = (width - len(lines[i])) if i < len(lines) else width
        line_padding_count = line_padding_count - separators_count - empty_spaces_count
        line_padding = " " * line_padding_count
        complete_line = line_content + line_padding
        print(f"\033[{y + lines_printed};{x}H", end="")
        print(f"| {complete_line} |")
        lines_printed += 1
    lines_printed += draw_line(width, x, y + lines_printed)
    if (len(footer) > 0):
        lines_printed = draw_footer(footer, width, x, y + lines_printed)
    return lines_printed

__all__ = ['draw_line', 'draw_title', 'draw_screen', 'change_cursor_pos', 'set_background_and_font_default_color', 'set_background_and_font_highlight_color']