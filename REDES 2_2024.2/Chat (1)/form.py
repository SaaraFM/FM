from screen import draw_line, draw_title, change_cursor_pos, set_background_and_font_default_color


"""
name: draw_field
kind: function
arguments: 
    - field: label do campo
    - label_width: tamanho do label
    - value_width: tamanho reservado à digitação do valor do campo 
    - x: posição horizontal do campo
    - y: posição vertical do campo
return: quantidade de linhas renderizadas na tela
description: Desenha um campo de um formulário. Exemplo: | Campo |[espaço reservado para a digitação do valor] | 
"""
def draw_field(field, label_width, value_width, x=0, y=0):
    label_separators_count = 2
    label_empty_spaces_count = 2
    value_separators_count = 1
    value_empty_spaces_count = 2
    label_padding = " "*(label_width - len(field))
    value_padding = " "*(value_width - label_separators_count - label_empty_spaces_count - value_separators_count - value_empty_spaces_count)
    change_cursor_pos(x, y)
    print(f"| {field}{label_padding} | {value_padding} |")
    lines_printed = 1
    return lines_printed

##########################################################################################

"""
name: draw_form
kind: function
arguments: 
    - title: título do formulário
    - fields: lista contendo o nome dos campos 
    - width: largura total do formulário
    - x: posição horizontal do campo
    - y: posição vertical do campo
return: quantidade de linhas renderizadas na tela
description: Desenha um campo de um formulário. Exemplo: | Campo |[espaço reservado para a digitação do valor] | 
"""
def draw_form(title, fields, width, x=0, y=0):
    longest_field_name = len(max(fields, key=len))
    set_background_and_font_default_color()
    lines_printed = draw_title(title, width, x, y)        
    for field in fields:        
        draw_field(field, longest_field_name, width - longest_field_name, x, y + lines_printed)
        lines_printed += 1
        lines_printed += draw_line(width, x, y + lines_printed)    
    return lines_printed

##########################################################################################

"""
name: execute_form
kind: function
arguments: 
    - title: título do formulário
    - fields: lista contendo o nome dos campos 
    - width: largura total do formulário
    - x: posição horizontal do campo
    - y: posição vertical do campo
return: dicionário contendo os campos e seus valores
description: desenha um formulário e depois executa a função input para capturar o texto digitado pelo usuário
"""
def execute_form(title, fields, width, x=0, y=0):
    draw_form(title, fields, width, x, y)
    form_data = {}
    separators_count = 3
    empty_spaces_count = 3
    x_position = x + len(max(fields, key=len)) + separators_count + empty_spaces_count
    y_position = y + 3
    for field in fields:
        form_data[field] = input(f"\033[{y_position};{x_position}H")
        while len(form_data[field]) == 0:
            form_data[field] = input(f"\033[{y_position};{x_position}H")
        y_position += 2
    return form_data

__all__ = ['execute_form']