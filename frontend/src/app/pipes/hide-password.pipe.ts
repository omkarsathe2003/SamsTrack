import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'hidePassword'
})
export class HidePasswordPipe implements PipeTransform {

  transform(input: string | null | undefined): string {

    if (!input) {
      return '';
    }

    return '*'.repeat(input.length);
  }

}