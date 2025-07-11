<?php

namespace App\Http\Requests;

use App\Rules\DateMultiFormat;
use Illuminate\Foundation\Http\FormRequest;

class StoreCoachRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        $baseRules = [
            'school_id' => 'required|string|exists:schools,id',
            'name' => 'required|max:255',
            'description' => 'required|string'
        ];

        $availabilityRules = [];
        $days = [
            'monday',
            'tuesday',
            'wednesday',
            'thursday',
            'friday',
            'saturday'
        ];

        foreach ($days as $day) {
            $availabilityRules["is_available_{$day}"] = 'required|boolean';
            $availabilityRules["{$day}_start_time"] = [
                "required_if_accepted:is_available_{$day}",
                new DateMultiFormat(['H:i', 'H:i:s'])
            ];
            $availabilityRules["{$day}_end_time"] = [
                "required_if_accepted:is_available_{$day}",
                new DateMultiFormat(['H:i', 'H:i:s'])
            ];
        }

        return array_merge($baseRules, $availabilityRules);
    }
}
